// src/app/models/user.interface.ts
export interface User {
    id?: number;
    username: string;
    name: string;
    address: string;
    email: string;
    password?: string; // Opcional para respuestas (por seguridad)
    roleId: number;
}

export interface CreateUserRequest {
    username: string;
    name: string;
    address: string;
    email: string;
    password: string;
    roleId: number;
}

export interface UpdateUserRequest {
    username?: string;
    name?: string;
    address?: string;
    email?: string;
    password?: string;
    roleId?: number;
}

export interface UserResponse {
    id: number;
    username: string;
    name: string;
    address: string;
    email: string;
    roleId: number;
}

export interface UserListParams {
    limit?: number;
    offset?: number;
    order?: string;
    sort?: string;
}

export interface LoginCredentials {
    email: string;
    password: string;
}

// Enum para roles (puedes expandir según tu sistema)
export enum UserRole {
    USER = 1,
    ADMIN = 2
}
