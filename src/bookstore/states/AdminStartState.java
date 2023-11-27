package bookstore.states;

import bookstore.ui.AdminStartPage;

public class AdminStartState implements ApplicationState {
  AdminStartPage adminStartPage = new AdminStartPage();
  @Override
  public ApplicationState transitionState(ActionsEnum action) {
    switch(action){
      case BOOKS:
        return new AdminBookState();
      case CUSTOMERS:
        return new AdminCustomerState();
      case LOGOUT:
        return new LoginState();
      default:
        return this;
    }
  }

    @Override
    public void renderState() {
        java.awt.EventQueue.invokeLater(() -> {
            adminStartPage.setVisible(true);
        });
    }

    @Override
    public void unrenderState() {
        java.awt.EventQueue.invokeLater(() -> {
            adminStartPage.setVisible(false);
        });
    }
}
