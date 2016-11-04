//次文件限制input 只能输入数字
function keyUp(obj){
    obj.value=obj.value.replace(/\D/g,'');
}

function afterpaste(obj){
    obj.value=obj.value.replace(/\D/g,'');
}

//限制可以输入数字和小数点 (正负数)
function keyUp2(obj){
    obj.value=obj.value.replace(/[^\-?\d.]/g,'');
}

function afterpaste2(obj){
    obj.value=obj.value.replace(/[^\-?\d.]/g,'');
}