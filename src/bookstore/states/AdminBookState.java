package bookstore.states;

import bookstore.ui.AdminBooksPage;

public class AdminBookState implements ApplicationState {
  AdminBooksPage adminBooksPage = new AdminBooksPage();
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
        adminBooksPage.setVisible(true);
    });
  }

  @Override
  public void unrenderState() {
    java.awt.EventQueue.invokeLater(() -> {
        adminBooksPage.setVisible(false);
    });
  }
}
