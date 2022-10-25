package crom.rental.service.bill;

interface BillQuery {
  String GET_BILL_BY_PERIOD = "select * from rental.bill b where 1=1 and b.rental_period = :rental_period";
}
