var serverIp = "218.61.208.71:8088";//服务器"218.61.208.71:8088"  //本地"192.168.31.168:8081"
var websocketUrl = "ws://" + serverIp + "/HDDTProject/websocket";
/**
 *  @author Pablo.风暴洋
 *  @company 大连创模科技
 *  @time 2020/4/24 11:10
 *  @package
 *  @characterization web端websocket实现
 */
var websocket;
var showTest;
var lockReconnect = false;//避免重复连接
var tt;

getUserIP(function (ip) {
    console.log("ip ++ " + ip);
});

function createWebSocket() {
    try {

        websocket = new WebSocket(websocketUrl);
        console.log("已经创建连接对象");
        init();


    } catch (e) {
        console.log('catch');
        reconnect(websocketUrl);
    }
}

function init() {
    websocket.onclose = function () {
        setMessageInnerHTML('链接关闭');
        reconnect(websocketUrl);
    };
    websocket.onerror = function () {
        setMessageInnerHTML('发生异常了');
        reconnect(websocketUrl);
    };
    websocket.onopen = function () {
        //心跳检测重置
        heartCheck.start();
    };
    // websocket.onmessage = function (event) {
    //     //拿到任何消息都说明当前连接是正常的
    //     setMessageInnerHTML('接收到消息');
    //     heartCheck.start();
    // };

    //监听窗口关闭，当窗口关闭之时去主动关闭连接，防止连接还没断开就关闭窗口，服务端就会抛异常
    websocket.onbeforeunload = function () {
        websocket.onclose = function () {
        };
        closeWebSocket();
    };
}

function isConn() {
    return websocket.readyState === websocket.CONNECTING;
}

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
                heartCheck.start();
            }
        }
    };
}

function reconnect(url) {
    if (lockReconnect) {
        return;
    }

    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    tt && clearTimeout(tt);
    tt = setTimeout(function () {
        createWebSocket(url);
        lockReconnect = false;
    }, 4000);
}

// if (window.WebSocket) {
//     websocket = new WebSocket(websocketUrl);
//
//     //连接错误回调
//     websocket.onerror = function () {
//         setMessageInnerHTML('连接错误！！');
//     };
//
//     //连接成功回调
//     websocket.onopen = function () {
//         setMessageInnerHTML('连接成功');
//     };
//
//     /**
//      * 在其他界面调用这个方法
//      * @param callback
//      */
//     function onMessage(callback) {
//
//         //收到消息回调
//         websocket.onmessage = function (ev) {
//             if (websocket.readyState != websocket.OPEN)
//                 return
//             else {
//                 if (ev.data != '') {
//                     var c = ev.data;
//                     callback(c);
//                     setMessageInnerHTML(ev.data);
//                 }
//             }
//         };
//     }
//
//     //连接关闭回调
//     websocket.onclose = function () {
//         setMessageInnerHTML('连接关闭');
//     };
//
//
//     //监听窗口关闭，当窗口关闭之时去主动关闭连接，防止连接还没断开就关闭窗口，服务端就会抛异常
//     websocket.onbeforeunload = function () {
//         websocket.onclose = function () {}
//         closeWebSocket();
//     };
//
//
// }


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

/**
 * JavaScript Web心跳检测,UnityEngine和JavaVM都可以监听的到
 *
 * @type serverTimeoutObj: java端返回信息的脉冲回调,
 * @type timeoutObj      : web端发送信息的脉冲回调,
 * @type start           : 启动键,
 * @type timeout         : 延迟帧
 */
var heartCheck = {
    timeout: 3000,
    timeoutObj: null,
    serverTimeoutObj: null,
    start: function () {
        console.log('start -->> 执行启动键');
        var self = this;
        this.timeoutObj && clearTimeout(this.timeoutObj);
        this.serverTimeoutObj && clearTimeout(this.serverTimeoutObj);
        this.timeoutObj =
            (function () {
                //这里发送一个心跳，后端收到后，返回一个心跳消息，
                //onmessage拿到返回的心跳就说明连接正常
                websocket.send('{"Web":"Heart rate normal"}');
                self.serverTimeoutObj = setTimeout(function () {
                    console.log(websocket);
                    websocket.close();
                    // createWebSocket();
                }, self.timeout);

            }, this.timeout)
    }
};

//网页界面一打开就创建一个websocket
createWebSocket(websocketUrl);


function getUserIP(onNewIP) { //  onNewIp - your listener function for new IPs
    //compatibility for firefox and chrome
    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new myPeerConnection({
            iceServers: []
        }),
        noop = function () {
        },
        localIPs = {},
        ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
        key;

    function iterateIP(ip) {
        if (!localIPs[ip]) onNewIP(ip);
        localIPs[ip] = true;
    }

    //create a bogus data channel
    pc.createDataChannel("");

    // create offer and set local description
    pc.createOffer().then(function (sdp) {
        sdp.sdp.split('\n').forEach(function (line) {
            if (line.indexOf('candidate') < 0) return;
            line.match(ipRegex).forEach(iterateIP);
        });

        pc.setLocalDescription(sdp, noop, noop);
    }).catch(function (reason) {
        // An error occurred, so handle the failure to connect
    });

    //sten for candidate events
    pc.onicecandidate = function (ice) {
        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
    };
}