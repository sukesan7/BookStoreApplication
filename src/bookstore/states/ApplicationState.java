package bookstore.states;

public interface ApplicationState {
  ApplicationState transitionState(ActionsEnum action);
  void renderState();
  void unrenderState();
}