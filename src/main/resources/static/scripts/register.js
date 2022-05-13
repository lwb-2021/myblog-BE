const register = () => {
  const form = document.getElementById("register");
  form.submit()
}
const checkAndRegister = () => {
  const email = document.getElementById("email")
  const username = document.getElementById("username");
  const password = document.getElementById("password");
  const confirm_password = document.getElementById("confirm_password")
  if(!username.value){
    alert("用户名不能为空！");
    return false;
  }else if(!password.value){
    alert("密码不能为空！");
    return false;
  }else if(!email.value){
    alert("邮箱不能为空！");
    return false;
  }else if(!confirm_password.value){
    alert("确认密码不能为空！");
    return false;
  }else if(password.value !== confirm_password.value){
    alert("密码和确认密码不一致！");
    return false;
  }else {
    register()
    return true;
  }
}