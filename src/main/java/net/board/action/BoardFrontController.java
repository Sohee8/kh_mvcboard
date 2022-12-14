package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String	requestURI = request.getRequestURI();
		String	contextPath = request.getContextPath();
		String	command = requestURI.substring(contextPath.length());
		
		ActionForward	forward = null;
		Action			action = null;
		
		System.out.println("BoardFrontController.doProcess(): requestURI|" + requestURI + "|");
		System.out.println("BoardFrontController.doProcess(): contextPath|" + contextPath + "|");
		System.out.println("BoardFrontController.doProcess(): command|" + command + "|");
		
		// 1.
		if (command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
	 // 2.
		} else if (command.equals("/BoardReplyAction.bo")) {
			action = new  BoardReplyAction();
			 
		       try { forward = action.execute(request, response); } catch (Exception e) {
		                  e.printStackTrace(); }
		// 3. 
			} else if (command.equals("/BoardDelete.bo")) { 
				forward = new ActionForward(); 
				forward.setRedirect(false);
			    forward.setPath("./board/qna_board_delete.jsp");
			
	  // 4.
		 } else if (command.equals("/BoardModify.bo")) { 
			    action = new  BoardModifyView();
			  
			 try { forward = action.execute(request, response);
			   } catch (Exception e) {
			                     e.printStackTrace(); }
			 
		// 5.
		} else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			 // 6.
			} else if (command.equals("/BoardReplyView.bo")) { 
				  action = new	 BoardReplyView();
			 
			 try { 
				 
				 forward = action.execute(request, response);
			 
			 } catch (Exception e) {
			        e.printStackTrace(); }
			 
			 
			 // 7. 
			 } else if  (command.equals("/BoardModifyAction.bo")) {
				     action = new BoardModifyAction();
			 
			     try { forward = action.execute(request, response); } catch (Exception e) {
			                       e.printStackTrace(); }
			  
			 // 8.
			   } else if (command.equals("/BoardDeleteAction.bo")) { 
				   action = new  BoardDeleteAction();
			 
			 try { forward = action.execute(request, response); } catch (Exception e) {
			  e.printStackTrace(); }
		
	    //  9.
		} else if (command.equals("/BoardList.bo")) { 
		             action = new	  BoardListAction();
		 
		 try { forward = action.execute(request, response);
		  } catch (Exception e) {
		                  e.printStackTrace(); }
		
      //  10.			 
	  } else if (command.equals("/BoardDetailAction.bo")) { 
		    action = new 	 BoardDetailAction();
			 
			 try { forward = action.execute(request, response); 
			 } catch (Exception e) { e.printStackTrace(); }
			 
		}
		
		// Action interface??? execute ?????? ???, 
		// ????????? ?????? forward ??????
		if (forward != null) {
			// 
			if (forward.isRedirect()) {
				// ?????? ????????? Redirection
				response.sendRedirect(forward.getPath());
				
			} else {
				// Action??? request??? Attribute??? ????????? ???????????? ????????????
				// View ???????????? ????????????.
				
				RequestDispatcher	dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
