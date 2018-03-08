package javastory.ui.window;

import javastory.ui.view.BoardPaneView;
import javastory.ui.view.ClubPaneView;
import javastory.ui.view.DetailMainView;
import javastory.ui.view.FxStoryMainView;
import javastory.ui.view.TravelPaneView;

public class WindowLycler {
	//
	private static WindowLycler instance;
	
	private FxStoryMainView mainView;
	private DetailMainView detailView;
	private WelcomeWindow welcomeWindow;
	private LoginWindow loginWindow;
	private SignupWindow signupWindow;
	private ClubPaneView clubPaneView;
	private BoardPaneView boardPaneView;
	private TravelPaneView travelPaneView;
	private AlertWindow alertWindow;
	
	private WindowLycler() {
		//
	}
	
	public synchronized static WindowLycler shareInstance() {
		//
		if (instance == null) {
			instance = new WindowLycler();
		}
		return instance;
	}
	
	public FxStoryMainView requestMainView() {
		//
		if (mainView == null) {
			mainView = new FxStoryMainView();
		}
		return mainView;
	}
	
	public DetailMainView requestDetailView() {
		//
		if (detailView == null) {
			detailView = new DetailMainView();
		}
		return detailView;
	}
	
	public WelcomeWindow requestWelcomeWindow() {
		//
		if (welcomeWindow == null) {
			welcomeWindow = new WelcomeWindow();			
		}
		return welcomeWindow;
	}
	
	public LoginWindow requestLoginWindow() {
		//
		if (loginWindow == null) {
			loginWindow = new LoginWindow();
		}
		return loginWindow;
	}
	
	public SignupWindow requestSignupWindow() {
		//
		if (signupWindow == null) {
			signupWindow = new SignupWindow();
		}
		return signupWindow;
	}
	
	public ClubPaneView requestClubPaneView() {
		//
		if (clubPaneView == null) {
			clubPaneView = new ClubPaneView();
		}
		return clubPaneView;
	}
	
	public BoardPaneView requestBoardPaneView() {
		//
		if (boardPaneView == null) {
			boardPaneView = new BoardPaneView();
		}
		return boardPaneView;
	}
	
	public TravelPaneView requestTravelPaneView() {
		//
		if (travelPaneView == null) {
			travelPaneView = new TravelPaneView();
		}
		return travelPaneView;
	}
	
	public NewClubWindow requestNewClubWindow() {
		//
		return new NewClubWindow();
	}
	
	public AlertWindow requestAlertWindow() {
		//
		if (alertWindow == null) {
			alertWindow = new AlertWindow();
		}
		return alertWindow;
	}
	
	public PostingWindow requestPostingWindow() {
		//
		return new PostingWindow();		
	}
	
	public CashBookWindow requestCashBookWindow() {
		//
		return new CashBookWindow(); 
	}
}