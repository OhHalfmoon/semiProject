console.log("Address Module")

let xhr = new XMLHttpRequest();
// xhr.open()
// xhr.send()

let addressService = (function () {
    // 주소 추가
    function add(address, callback) {
        console.log("add() :: " + address)
        console.log(address)
        $.post({
          url : cp + "/addr/new",
          data : JSON.stringify(address),
          dataType : "json",
          contentType : "application/json; charset=utf-8"
        })
        .done(function(data) {
        if (callback) {
            callback(data);
        }
      })
    }


    // 주소 목록 조회
    function getList(param, callback) {


    	let url = cp + "/addr/list/" + param.memberNo;
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



 // 메인 주소 수정
    function modifyMain(address, callback, error) {
      console.log("modifyMain()")
      console.log(address)
      $.ajax({
        url : cp + "/addr/" + address.addrNo,
        method : 'put',        
        data : JSON.stringify(address),
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

    // 주소 수정
    function modify(address, callback, error) {
	  console.log("modify()")
	  console.log(address)
	  $.ajax({
	    url : cp + "/addr/up/" + address.addrNo,
	    method : 'put',        
	    data : JSON.stringify(address),
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
    
    // 주소 삭제
	function remove(addrNo, callback, error) {
	    $.ajax({
	      url : cp + "/addr/del/" + addrNo,
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
        modifyMain : modifyMain,
        modify : modify,
        remove : remove
    };
})()





