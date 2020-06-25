console.log("Reply Module.....")

//자동 호출 함수를 사용
var replyService=( function(){
	
	console.log("Reply Module........")
	
	//댓글 추가
	function add(reply, callback,error){
		console.log("add reply........");
		
		$.ajax({

			type:"post",
			url:"/replies/new",
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글 목록 출력
	function getList(param, callback, error){
		
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data){
					if (callback){
//						callback(data);
						callback(data.replyCnt, data.list);
					}
				}).fail(function(xhr, status, err){
							if (error){
								error();
							}
						});
	}
	
	//댓글 삭제
	function remove(rno, callback, error){
		$.ajax({
			type:"delete",
			url:"/replies/" + rno,
			success:function(deleteResult,status,xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글 수정
	function update(reply, callback, error) {
		
		console.log("RNO : " + reply.rno);
		
		$.ajax({
			type:"put",
			url:"/replies/" + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", function(result){
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',	
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}
	;
	
	
	
	
	
	return {
		add:add,  //앞의 add는 속성명, 뒤의 add는 함수의 이름 add에 의해서 함수 add가 호출
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime:displayTime
	};
	
})()
// 뒤의 ()는 앞의 ()내부에 생성된 function을 바로 호출

