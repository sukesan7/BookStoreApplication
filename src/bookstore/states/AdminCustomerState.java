package bookstore.states;
import bookstore.ui.AdminCustomersPage;
public class AdminCustomerState implements ApplicationState {
  private AdminCustomersPage adminCustomersPage = new AdminCustomersPage();
  @Override
  public ApplicationState transitionState(ActionsEnum action) {
    switch(action){
      case BACK:
        return new AdminStartState();
      default:
        return this;
    }
  }
  
  @Override
  public void renderState() {
    java.awt.EventQueue.invokeLater(() -> {
        adminCustomersPage.setVisible(true);
    });
  }

  @Override
  public void unrenderState() {
    java.awt.EventQueue.invokeLater(() -> {
        adminCustomersPage.setVisible(false);
    });
  }
}
