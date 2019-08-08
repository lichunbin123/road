import axios from 'axios'
export { loginProc, logout,getUserInfo}

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`
}
enhanceAccessToeken()
function loginProc (data) {
  const url = `http://localhost:8083/login`
  return axios({
    method: 'post',
    url: url,
    data: data
  }).then(res => {
    localStorage.accessToken = res.data
    axios.defaults.headers.common['Authorization'] = `Bearer ${res.data}`
  })
    .catch(function (err) {
      console.log(err.response)
      console.log(err.response.status)
    })
}

function getUserInfo () {
  const url = `http://localhost:8083/getUserMessage`
  return axios.post(url).then((res) => res.data)
}


function logout () {
  delete localStorage.accessToken
  axios.defaults.headers.common['Authorization'] = undefined
}
