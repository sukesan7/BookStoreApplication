package bookstore.states;

import bookstore.ui.CustomerCostPage;

public class CustomerCostState implements ApplicationState {
  private final CustomerCostPage customerCostPage = new CustomerCostPage();
  @Override
  public ApplicationState transitionState(ActionsEnum action) {
    switch(action){
      case LOGOUT:
        return new LoginState();
      default:
        return this;
    }
  }
  
  @Override
  public void renderState() {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        customerCostPage.setVisible(true);
    });
  }

  @Override
  public void unrenderState() {
    java.awt.EventQueue.invokeLater(() -> {
        customerCostPage.setVisible(false);
    });
  }
}