import { Injectable, signal, computed } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


import {
  User,
  CreateUserRequest,
  UpdateUserRequest,
  UserResponse,
  UserListParams
} from '../models/user.interface';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private baseUrl = 'http://localhost:8080';

  // Signal para mantener la lista de usuarios
  private usersSignal = signal<UserResponse[]>([]);
  public users = this.usersSignal.asReadonly();
  public totalUsers = computed(() => this.usersSignal().length);

  constructor(private http: HttpClient) { }

  // GET /users - Obtener todos los usuarios con parámetros opcionales
  getAllUsers(params?: UserListParams): Observable<UserResponse[]> {
    let httpParams = new HttpParams();


    return this.http.get<UserResponse[]>(`${this.baseUrl}/users`, { params: httpParams })
      .pipe(
        map(users => {
          this.usersSignal.set(users);
          return users;
        }),
        catchError(this.handleError)
      );
  }

  // GET /users con paginación específica
  getUsersPaginated(limit: number = 10, offset: number = 0): Observable<UserResponse[]> {
    return this.getAllUsers({ limit, offset });
  }

  // POST /users - Crear nuevo usuario
  createUser(user: CreateUserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/users`, user)
      .pipe(
        map(newUser => {
          // Actualizar signal local
          this.usersSignal.update(currentUsers => [...currentUsers, newUser]);
          return newUser;
        }),
        catchError(this.handleError)
      );
  }

  // PUT /users/{id} - Actualizar usuario existente
  updateUser(id: number, user: UpdateUserRequest): Observable<UserResponse> {
    if (id <= 0) {
      return throwError(() => new Error('ID must be positive'));
    }

    return this.http.put<UserResponse>(`${this.baseUrl}/users/${id}`, user)
      .pipe(
        map(updatedUser => {
          // Actualizar signal local
          this.usersSignal.update(currentUsers =>
            currentUsers.map(u => u.id === id ? updatedUser : u)
          );
          return updatedUser;
        }),
        catchError(this.handleError)
      );
  }

  // DELETE /users/{id} - Eliminar usuario
  deleteUser(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/users/${id}`)
      .pipe(
        map(response => {
          // Remover del signal local
          this.usersSignal.update(currentUsers =>
            currentUsers.filter(u => u.id !== id)
          );
          return response;
        }),
        catchError(this.handleError)
      );
  }

  // Métodos de utilidad
  getUserById(id: number): UserResponse | undefined {
    return this.usersSignal().find(user => user.id === id);
  }

  getUserByEmail(email: string): UserResponse | undefined {
    return this.usersSignal().find(user => user.email === email);
  }

  getUsersByRole(roleId: number): UserResponse[] {
    return this.usersSignal().filter(user => user.roleId === roleId);
  }

  // Validaciones del lado cliente (basadas en tus anotaciones JPA)
  validateUser(user: Partial<CreateUserRequest>): string[] {
    const errors: string[] = [];

    if (!user.username || user.username.trim().length === 0) {
      errors.push('Username es requerido');
    } else if (user.username.length > 50) {
      errors.push('Username no puede exceder 50 caracteres');
    }

    if (!user.name || user.name.trim().length === 0) {
      errors.push('Name es requerido');
    } else if (user.name.length > 100) {
      errors.push('Name no puede exceder 100 caracteres');
    }

    if (!user.address || user.address.trim().length === 0) {
      errors.push('Address es requerido');
    } else if (user.address.length > 500) {
      errors.push('Address no puede exceder 500 caracteres');
    }

    if (!user.email || user.email.trim().length === 0) {
      errors.push('Email es requerido');
    } else if (user.email.length > 100) {
      errors.push('Email no puede exceder 100 caracteres');
    } else if (!this.isValidEmail(user.email)) {
      errors.push('Formato de email inválido');
    }

    if (!user.password || user.password.trim().length === 0) {
      errors.push('Password es requerido');
    } else if (user.password.length > 50) {
      errors.push('Password no puede exceder 50 caracteres');
    }

    if (user.roleId === undefined || user.roleId < 1) {
      errors.push('Role ID es requerido y debe ser mayor a 0');
    }

    return errors;
  }

  private isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  // Manejo de errores
  private handleError = (error: HttpErrorResponse): Observable<never> => {
    let errorMessage = 'Ha ocurrido un error desconocido';

    if (error.error instanceof ErrorEvent) {
      // Error del lado cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado servidor
      switch (error.status) {
        case 400:
          errorMessage = error.error?.message || 'Solicitud inválida';
          break;
        case 404:
          errorMessage = 'Usuario no encontrado';
          break;
        case 500:
          errorMessage = 'Error interno del servidor';
          break;
        default:
          errorMessage = `Error ${error.status}: ${error.message}`;
      }
    }

    console.error('UserService Error:', error);
    return throwError(() => new Error(errorMessage));
  };

  // Limpiar signal (útil para logout o refresh)
  clearUsers(): void {
    this.usersSignal.set([]);
  }
}