"use strict";

export const PI = 3.1416;

function f1(a, b) {
    return a + b;
}

const f2 = function (a, b) {
    return a - b;
}
console.log(f2(5, 3));
const f3 = (a, b) => { return a * b };
export const f4 = (a) => a ** 3;

async function getAlumnos() {
    try {
        const response = await fetch('http://localhost:8080/api/alumnos');

        const respJson = await response.json();
        return respJson;
    } catch (err) {
        console.error("Error al obtener los alumnos:", err);
    }
}

async function createAlumno() {
    try {
        let nAlumno = await fetch('http://localhost:8080/api/alumnos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                cedula: 121212,
                primerNombre: 'Emanuel',
                segundoNombre: 'Andres',
                primerApellido: 'Gomez',
                segundoApellido: 'Gomez'
            })
        });
        console.log("Alumno creado:", await nAlumno.json());
    } catch (error) {
        console.error("Error al crear el alumno:", error);
    }
}

// fetch('http://localhost:8020/api/alumnos')
//     .then(res => res.json())
//     .then(data => console.log(data))
//     .catch(err => console.log(err));
// fetch('https://jsonplaceholder.typicode.com/todos/1')
//     .then(response => response.json())
//     .then(json => console.log(json))
//     .catch(err => console.log(err));
export { f1, f2, f3, getAlumnos, createAlumno };