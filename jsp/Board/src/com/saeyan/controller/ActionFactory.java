package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardCheckPassAction;
import com.saeyan.controller.action.BoardCheckPassFormAction;
import com.saeyan.controller.action.BoardDeleteAction;
import com.saeyan.controller.action.BoardListAction;
import com.saeyan.controller.action.BoardUpdateAction;
import com.saeyan.controller.action.BoardUpdateFormAction;
import com.saeyan.controller.action.BoardViewAction;
import com.saeyan.controller.action.BoardWriteAction;
import com.saeyan.controller.action.BoardWriteFormAction;
import com.saeyan.controller.action.ReplyWriteAction;

//Singleton 패턴적용
public class ActionFactory {
	private static ActionFactory instance=new ActionFactory();
	
	//기본 생성자
	private ActionFactory(){
		super();
	}
	
	public static ActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){
		Action action=null;
		System.out.println("ActionFactory :"+command);
		
		if (command.equals("board_list")) {
			action = new BoardListAction();
		}else if (command.equals("board_write_form")){
			action = new BoardWriteFormAction();
		}else if (command.equals("board_write")){
			action=new BoardWriteAction();
		}else if (command.equals("board_view")){
			action=new BoardViewAction();
		}else if (command.equals("board_check_pass_form")){
			//수정,삭제시 비밀번호 입력창을 띄울 때 처리
			action=new BoardCheckPassFormAction();			
		}else if (command.equals("board_check_pass")){
			//수정,삭제시 비밀번호확인 처리
			action=new BoardCheckPassAction();
		}else if (command.equals("board_update_form")){
			//수정폼을 띄울 때 처리
			action=new BoardUpdateFormAction();
		}else if (command.equals("board_update")){
			//게시글을 update처리
			action=new BoardUpdateAction();
		}else if (command.equals("board_delete")){
			action=new BoardDeleteAction();
		}else if (command.equals("reply_write")){
			action=new ReplyWriteAction();
		}
		
		return action;
	}
}
