console.log("Reply Module")

let xhr = new XMLHttpRequest();
// xhr.open()
// xhr.send()

let replyService = (function () {
    // 댓글 추가
    function add(reply, callback) {
        console.log("add() :: " + reply)
        console.log(reply)
        $.post({
          url : cp + "/replies/new",
          data : JSON.stringify(reply),
          dataType : "json",
          contentType : "application/json; charset=utf-8"
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
    }


    // 댓글 목록 조회
    function getList(param, callback) {

//         let url = cp + "/board/" +param.bno;
    	let url = cp + "/replies/list/" + param.bno + "/" + (param.rno || "");
        console.log(url);
        $.getJSON(url)
        .done(function(data) {
        	console.log(data);
			if(callback) {
				callback(data);
				console.log(data);	
			}
		})
    }
// 댓글 단일 조회
    function get(bno, callback) {
		var url = cp + "/replies/" + bno;
		console.log(url);
	    $.getJSON(url)
	    .done(function(data) {
	      if (callback) {
	        callback(data);
	      }
	    })
	}


 // 댓글 수정
    function modify(reply, callback, error) {
      console.log("modify()")
      console.log(reply)
      $.ajax({
        url : cp + "/replies/" + reply.bno,
        method : 'put',        
        data : JSON.stringify(reply),
        dataType : "json",
        contentType : "application/json; charset=utf-8"
      })
      .done(function(data) {
      if (callback) {
          callback(data);
      }      
    })
    .fail(function(xhr) {
      console.log(xhr);
    })
  }
   
    return {
        add : add,
        getList : getList,
        get :get,
        modify : modify
        
    };
})()

let tradeService = (function () {
    // 거래 생성
    function add(trade, callback) {
        console.log("add() :: " + trade)
        console.log(trade)
        $.post({
          url : cp + "/trades/new",
          data : JSON.stringify(trade),
          dataType : "json",
          contentType : "application/json; charset=utf-8"
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
    }


// 거래 목록 조회
    function getList(param, callback) {

    	let url = cp + "/trades/list/" + param.memberNo;
        console.log(url);
        $.getJSON(url)
        .done(function(data) {
        	console.log(data);
			if(callback) {
				callback(data);
				console.log(data);	
			}
		})
    }
    
// 거래 단일 조회
    function get(bno, callback) {
		var url = cp + "/trades/" + bno;
		console.log(url);
	    $.getJSON(url)
	    .done(function(data) {
	      if (callback) {
	        callback(data);
	      }
	    })
	}


 // 거래 수정
    function modify(trade, callback, error) {
    	
      console.log("modify()")
      console.log(trade)
      $.ajax({
        url : cp + "/trades/" + trade.tradeNo,
        method : 'put',        
        data : JSON.stringify(trade),
        dataType : "json",
        contentType : "application/json; charset=utf-8"
      })
      .done(function(data) {
      if (callback) {
          callback(data);
      }      
    })
    .fail(function(xhr) {
      console.log(xhr);
    })
  }

    // 거래요청 삭제
    function remove(trade, callback, error) {
        $.ajax({
          url : cp + "/trades/" + trade.tradeNo,
          method : 'delete',
          dataType : 'json'
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
      .fail(function(xhr) {
        console.log(xhr);
      })
    }    
   
    return {
        add : add,
       getList : getList,
        get :get,
        modify : modify,
        remove : remove
    };
})()

let reserveService = (function () {

// 거래 목록 조회
    function getList(param, callback) {

    	let url = cp + "/trades/list/reserve/" + param.memberNo;
        console.log(url);
        $.getJSON(url)
        .done(function(data) {
        	console.log(data);
			if(callback) {
				callback(data);
				console.log(data);	
			}
		})
    }
    

 // 거래 수정
    function modify(trade, callback, error) {
    	
      console.log("modify()")
      console.log(trade)
      $.ajax({
        url : cp + "/trades/" + trade.tradeNo,
        method : 'put',        
        data : JSON.stringify(trade),
        dataType : "json",
        contentType : "application/json; charset=utf-8"
      })
      .done(function(data) {
      if (callback) {
          callback(data);
      }      
    })
    .fail(function(xhr) {
      console.log(xhr);
    })
  }
    
    // 예약내역 삭제
    function remove(trade, callback, error) {
        $.ajax({
          url : cp + "/trades/" + trade.tradeNo,
          method : 'delete',
          dataType : 'json'
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
      .fail(function(xhr) {
        console.log(xhr);
      })
    }      
   
    return {
        
       getList : getList,        
        modify : modify,
        remove : remove        
    };
})()

let finishService = (function () {

// 거래 목록 조회
    function getList(param, callback) {

    	let url = cp + "/trades/list/finish/" + param.memberNo;
        console.log(url);
        $.getJSON(url)
        .done(function(data) {
        	console.log(data);
			if(callback) {
				callback(data);
				console.log(data);	
			}
		})
    }

   
    return {
        
       getList : getList       
    };
})()


let myService = (function () {

// 거래신청 목록 조회
    function getList(param, callback) {

    	let url = cp + "/trades/list/my/" + param.memberNo;
        console.log(url);
        $.getJSON(url)
        .done(function(data) {
        	console.log(data);
			if(callback) {
				callback(data);
				console.log(data);	
			}
		})
    }
    
    
    // 신청내역 삭제
    function remove(trade, callback, error) {
        $.ajax({
          url : cp + "/trades/my/" + trade.tradeNo,
          method : 'delete',
          dataType : 'json'
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
      .fail(function(xhr) {
        console.log(xhr);
      })
    }      
   
    return {
       getList : getList,        
        remove : remove        
    };
})()

