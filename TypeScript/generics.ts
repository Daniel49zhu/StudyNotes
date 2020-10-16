function identity<T>(arg: T): T {
    return arg;
}

let output = identity<string>("mystring");
//---------------------------------------------

interface GenericIdentityFn<T> {
    (arg: T) : T;
}

function identity2<T>(arg: T): T {
    return arg;
}

let myIdentity: GenericIdentityFn<number> = identity2;
//---------------------------------------------

class GenericNumber<T> {
    zeroVaue: T;
    add: (x: T,y: T) => T
}
let myGenericNumber = new GenericNumber<number>();
myGenericNumber.zeroVaue = 0;
myGenericNumber.add = function(x,y) {
    return x + y;
}
