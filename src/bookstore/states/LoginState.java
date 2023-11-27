package bookstore.states;

import bookstore.ui.LoginPage;
import bookstore.ui.BookStoreLauncher;

public class LoginState implements ApplicationState {
  LoginPage loginPage = new LoginPage();
  @Override
  public ApplicationState transitionState(ActionsEnum action) {
    switch(action){
      case ADMIN_LOGIN:
        /* Unrender current state */
        unrenderState();
        return new AdminStartState();
      case CUSTOMER_LOGIN:
        unrenderState();
        return new CustomerStartState();
      default:
        return this;
    }
  }
  
  @Override
  public void renderState(){
    java.awt.EventQueue.invokeLater(() -> {
        loginPage.setVisible(true);
    });
  }
  
  @Override
  public void unrenderState(){
    java.awt.EventQueue.invokeLater(() -> {
        loginPage.setVisible(false);
    });
  }
}
