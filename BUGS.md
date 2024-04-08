# Bug Reports

Modify Reservation
------------------

* Modify reservation does not work when you supply to a room number that already
  has a reservation tied to it
    * Fix: Add an error message that states room is already reserved
* Modify reservation REQUIRES you to enter a Room Number and Price
    * Issue 1: User cannot modify just one of these issues
    * Issue 2: If user tries to bypass this and enter the room number they
      already have, then the modify cannot go through because the room
      is already tied to the reservation we are trying to modify.
        * Fix: Don't require all cells to be filled
* Modify reservation does not check that start/end dates are before today
    * Fix: we should only allow people to make reservations for today

* Modify Reservation:
    1. Log in as LarryTheLobster
    2. Modify LarryTheLobster's reservation
    4. Save changes
    5. Click on LarryTheLobster's reservation
    6. Click 'Modify Selected Reservation'
    7. Cope:

```
Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException: Cannot invoke "edu.baylor.GroupFive.models.Reservation.getRoomNumber()" because "this.reservation" is null
        at edu.baylor.GroupFive.ui.modifyReservation.ModifyReservationPanel.addRoomPanel(ModifyReservationPanel.java:93)
        at edu.baylor.GroupFive.ui.modifyReservation.ModifyReservationPanel.<init>(ModifyReservationPanel.java:54)
        at edu.baylor.GroupFive.ui.utils.Page.onPageSwitch(Page.java:80)
        at edu.baylor.GroupFive.ui.reservations.ReservationsPanel.lambda$addButtonListeners$0(ReservationsPanel.java:94)
        at java.desktop/javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:1972)
        at java.desktop/javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2314)
        at java.desktop/javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:407)
        at java.desktop/javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:262)
        at java.desktop/javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:279)
        at java.desktop/java.awt.Component.processMouseEvent(Component.java:6621)
        at java.desktop/javax.swing.JComponent.processMouseEvent(JComponent.java:3398)
        at java.desktop/java.awt.Component.processEvent(Component.java:6386)
        at java.desktop/java.awt.Container.processEvent(Container.java:2266)
        at java.desktop/java.awt.Component.dispatchEventImpl(Component.java:4996)
        at java.desktop/java.awt.Container.dispatchEventImpl(Container.java:2324)
        at java.desktop/java.awt.Component.dispatchEvent(Component.java:4828)
        at java.desktop/java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4948)
        at java.desktop/java.awt.LightweightDispatcher.processMouseEvent(Container.java:4575)
        at java.desktop/java.awt.LightweightDispatcher.dispatchEvent(Container.java:4516)
        at java.desktop/java.awt.Container.dispatchEventImpl(Container.java:2310)
        at java.desktop/java.awt.Window.dispatchEventImpl(Window.java:2780)
        at java.desktop/java.awt.Component.dispatchEvent(Component.java:4828)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:775)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:720)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:714)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:400)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:87)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:98)
        at java.desktop/java.awt.EventQueue$5.run(EventQueue.java:747)
        at java.desktop/java.awt.EventQueue$5.run(EventQueue.java:745)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:400)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:87)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:744)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
```


Delete Reservation
------------------

* We are able to delete a reservation and have it reflect on the table
* HOWEVER, I am not sure if this is being updated in the database
* Bug reconstruction
    * Log-in
    * Delete both reservations tied to room 101
    * Try to modify a reservation and change room to 101
    * Confirm changes does not push any changes
        * Conclusion: room 101 reservation is just deleted from the table
          and NOT the database


Add Reservation
---------------

* Reservations are presumably created for the person currently logged in
* What if a client is logged-in? How do they create a reservation for a user?


Log-in
------

* Log-in does not verify password is correct, only that username is correct
