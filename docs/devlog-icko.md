# My Dev Log

05/03/2024
----------

*   \[**BUG**\] `ReservationServices.save` was returning `null` on success reservation creation.
    This resulted in an exception being thrown even if program did not visibly break.
    *   \[**FIX**\] `save` now implements `getAllActive` in conjuction with `insert` and `modify`
    *   Added test in `ReservationController.saveReservation` that checks for a
        `null` return from `ReservationServices.save`

