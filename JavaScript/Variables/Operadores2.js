// Operadores incremento / decrementi
let a, b, c;

a = 0;

//Pre-incremento
++a;
console.log(a); // primero suma 1, luego muestra -> 1

//Post-incremento
a++;
console.log(a); // primero muestra 1, luego suma -> (a queda en 2)

//Pre-drecrementi
--a;
console.log(a); // primero resta 1 (a = 1), luego muestra -> 1

//Post-decremento
a--;
console.log(a); // primero muestra 1, luego resta -> (a queda en 0)

//Ejemplo
a = 5;
b = 2;
c = ++a * b--;
console.log(c);

//Operadores de Asignacion

let number = 10;
console.log(number);

number = 20;
console.log(number);

//Operador +=
number += 5;
console.log(number);


//Operador -=
number -= 3;
console.log(number);

//Operador *=
number *= 2;
console.log(number);

//Operador /=
number /= 4;
console.log(number);

//Operador %=
number %= 2;
console.log(number);

//Operador **=
number = 8;
number **= 2;
console.log(number);


