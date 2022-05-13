
const convert2json = (formData) => {
  const objData = {};
  formData.forEach((value, key) => objData[key] = value);
  return JSON.stringify(objData);
};
const get_parameter = (name) =>{
  const reg_exp = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  const r = location.search.substr(1).match(reg_exp);
  if (r!=null) return (r[2]); return null;
}

const login = (username, password) => {
  const redirect = get_parameter("redirect");
  const form = document.getElementById("login");
  form.submit()
}
const checkAndLogin = () => {
  const username = document.getElementById("username");
  const password = document.getElementById("password");
  if(!username.value){
    alert("用户名不能为空！");
    return false;
  }else if(!password.value){
    alert("密码不能为空！");
    return false;
  }else {
    login(username.value, password.value)
    return true;
  }
}