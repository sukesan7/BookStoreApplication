package bookstore.states;

import bookstore.ui.CustomerStartPage;

public class CustomerStartState implements ApplicationState {
  private final CustomerStartPage customerStartPage = new CustomerStartPage();
  @Override
  public ApplicationState transitionState(ActionsEnum action) {
    switch(action){
      case PURCHASE:
        return new CustomerCostState();
      case LOGOUT:
        return new LoginState();
      default:
        return this;
    }
  }
  
  @Override
  public void renderState() {
    java.awt.EventQueue.invokeLater(() -> {
        customerStartPage.setVisible(true);
    });
  }

  @Override
  public void unrenderState() {
    java.awt.EventQueue.invokeLater(() -> {
        customerStartPage.setVisible(false);
    });
  }
}
