export function formatDate(date, fmt) {
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
	}
	var o = {
		'M+': date.getMonth() + 1,
		'd+': date.getDate(),
		'h+': date.getHours(),
		'm+': date.getMinutes(),
		's+': date.getSeconds()
	}
	for (var k in o) {
		if (new RegExp(`(${k})`).test(fmt)) {
			var str = o[k] + ''
			fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str))
		}
	}
	return fmt
}

function padLeftZero(str) {
	return ('00' + str).substr(str.length)
}

// 秒转换分钟00:00:00格式
export function timeToMinute(times){
    var result = '00:00:00'
    var hour,minute,second
    if (times > 0) {
      hour = Math.floor(times / 3600)
      if (hour < 10) {
        hour = "0"+hour
      }
      minute = Math.floor((times - 3600 * hour) / 60)
      if (minute < 10) {
        minute = "0"+minute
      }
 
      second = Math.floor((times - 3600 * hour - 60 * minute) % 60)
      if (second < 10) {
        second = "0"+second
      }
      if (hour == '00') {
	      result = minute+':'+second
      } else if (minute == '00'){
    	  result = hour+':'+minute+':'+second
      } else {
    	  result = second
      }
    }
    return result
}

// 将秒转为00:00的格式
export function secondsToMinuteColonSeconds(time){
	var milliseconds = time * 1000
	var date = new Date(milliseconds)
	return date.getMinutes() + ':' + date.getSeconds()
}
