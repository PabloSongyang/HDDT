var serverIp = "192.168.31.168:8081";
/**
 *  @author Pablo.风暴洋
 *  @company 大连创模科技
 *  @time 2020/4/24 11:10
 *  @package
 *  @characterization web端websocket实现
 */
var websocket;
var showTest;

if (window.WebSocket) {
    websocket = new WebSocket("ws://" + serverIp + "/HDDTProject/websocket");

    //连接错误回调
    websocket.onerror = function () {
        setMessageInnerHTML('连接错误！！');
    };

    //连接成功回调
    websocket.onopen = function () {
        setMessageInnerHTML('连接成功');
    };

    /**
     * 在其他界面调用这个方法
     * @param callback
     */
    function onMessage(callback) {

        //收到消息回调
        websocket.onmessage = function (ev) {
            if (websocket.readyState != websocket.OPEN)
                return
            else {
                if (ev.data != '') {
                    var c = ev.data;
                    callback(c);
                    setMessageInnerHTML(ev.data);
                }
            }
        };
    }

    //连接关闭回调
    websocket.onclose = function () {
        setMessageInnerHTML('连接关闭');
    };


    //监听窗口关闭，当窗口关闭之时去主动关闭连接，防止连接还没断开就关闭窗口，服务端就会抛异常
    websocket.onbeforeunload = function () {
        closeWebSocket();
    };


}


/**
 * 消息显示在网页上
 * @param innerHTML
 */
function setMessageInnerHTML(innerHTML) {
    // message.innerHTML += innerHTML + '<br/>';
    if (innerHTML != '') {
        showTest = innerHTML;
        console.log('获取消息 --->>> ' + innerHTML);
    }


}

/**
 * 关闭连接
 */
function closeWebSocket() {
    websocket.close();
}

/**
 * 发送消息
 */
function send(msg, type) {

    switch (type) {
        case 1:
            websocket.addEventListener('open', function () {
                console.log("msg -->> " + msg);
                websocket.send(msg.toString());
            });
            break;
        case 2:
            console.log("msg -->> " + msg);
            websocket.send(msg.toString());
            websocket.addEventListener('message', function () {

            });
            break;
        case 3:
            websocket.addEventListener('close', function () {
                console.log("msg -->> " + msg);
                websocket.send(msg.toString());
            });
            break;
    }

    // else if (type == 'close') {
    //     websocket.addEventListener('close', function () {
    //         websocket.send(msg.toString());
    //     });
    // }
}
