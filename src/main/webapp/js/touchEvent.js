/**
 *  @author Pablo.风暴洋
 *  @company 大连创模科技
 *  @time 2020/4/24 11:10
 *  @package
 *  @characterization 移动端手势事件
 */

var lastTouchEnd = 0;

/**
 * 禁止双指放大
 */
document.documentElement.addEventListener('touchstart', function (event) {
    if (event.touches.length > 1) {
        event.preventDefault();
    }
}, false);


/**
 * 禁止双击放大
 */
document.documentElement.addEventListener('touchend', function (event) {
    var now = Date.now();
    if (now - lastTouchEnd <= 300) {
        event.preventDefault();
    }
    lastTouchEnd = now;
}, false);