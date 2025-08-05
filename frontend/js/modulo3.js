class Modulo3{
    static cantidad = 0;
    #marca;
    #color;
    constructor(marca, color){
        Modulo3.cantidad++;
        this.#marca = marca;
        this.#color = color;
    }
    pitar(){
        console.log("Piiiii Piiii Piiii");
    }

    get marca(){
        return this.#marca;
    }

    set marca(marca){
        this.#marca = marca;
    }

    get color(){
        return this.#color;
    }
}
export default Modulo3;