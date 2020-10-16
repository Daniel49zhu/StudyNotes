function identity(arg) {
    return arg;
}
var output = identity("mystring");
function identity2(arg) {
    return arg;
}
var myIdentity = identity2;
//---------------------------------------------
var GenericNumber = /** @class */ (function () {
    function GenericNumber() {
    }
    return GenericNumber;
}());
var myGenericNumber = new GenericNumber();
myGenericNumber.zeroVaue = 0;
myGenericNumber.add = function (x, y) {
    return x + y;
};
