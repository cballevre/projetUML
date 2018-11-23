package sample.errors;

public class NonexistentView extends Exception {
    public NonexistentView() {
        System.out.println("The view is non-existent.");
    }
}
