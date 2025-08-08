// src/app/services/auth.service.ts (actualizado)
import { Injectable, signal, computed } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { UserService } from './user.service';
import { LoginCredentials, UserResponse } from '../models/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSignal = signal<UserResponse | null>(null);
  
  public currentUser = this.currentUserSignal.asReadonly();
  public isLoggedIn = computed(() => this.currentUserSignal() !== null);
  public isAdmin = computed(() => {
    const user = this.currentUserSignal();
    return user ? user.roleId === 2 : false; // Asumiendo que roleId 2 es admin
  });

  constructor(private userService: UserService) {
    this.loadStoredUser();
  }

  login(credentials: LoginCredentials): Observable<boolean> {
    // Obtener todos los usuarios y buscar por email
    return this.userService.getAllUsers().pipe(
      map(users => {
        const user = users.find(u => u.email === credentials.email);
        
        if (user) {
          // En un sistema real, validarías la contraseña en el backend
          // Por ahora, simplemente aceptamos cualquier contraseña no vacía
          if (credentials.password && credentials.password.length > 0) {
            this.setCurrentUser(user);
            return true;
          }
        }
        return false;
      }),
      catchError(error => {
        console.error('Login error:', error);
        return of(false);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSignal.set(null);
  }

  private setCurrentUser(user: UserResponse): void {
    // No guardamos password por seguridad
    const userWithoutPassword = { ...user };
    localStorage.setItem('currentUser', JSON.stringify(userWithoutPassword));
    this.currentUserSignal.set(userWithoutPassword);
  }

  private loadStoredUser(): void {
    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      try {
        const user = JSON.parse(storedUser);
        this.currentUserSignal.set(user);
      } catch (error) {
        console.error('Error loading stored user:', error);
        localStorage.removeItem('currentUser');
      }
    }
  }

  getCurrentUser(): UserResponse | null {
    return this.currentUserSignal();
  }

  hasRole(roleId: number): boolean {
    const user = this.currentUserSignal();
    return user ? user.roleId === roleId : false;
  }
}
