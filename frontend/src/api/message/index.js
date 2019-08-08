import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

var socket = new SockJS('http://localhost:8083/websocket')
var stompClient = Stomp.over(socket)

window.connected = false

export default {
  connect () {
    return stompClient.connect({},
      function (frame) {
      console.log("frame ==="+frame)
        window.connected = true
        stompClient.subscribe('/queue/chat', tick => {
          console.log("tick ==="+tick)
        })
      },
      function (error) {
        console.log("error ==="+error)
      })
  },
  sendMessage (data) {
    return stompClient.send(
      '/app/sendMessage',
      JSON.stringify(data)
    )
  },
  fetchMessage (data) {
    return stompClient.send(
      '/app/fetchMessage',
      JSON.stringify(data)
    )
  },
  getConnectStatus () {
    return window.connected
  }
}

