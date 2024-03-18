import javax.swing.SwingUtilities;

class ReserveRoom {
    public static void ReserveRoomGUI() {
        ReserveRoomFrame frame = new ReserveRoomFrame();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReserveRoomGUI();
            }
        });
    }
}