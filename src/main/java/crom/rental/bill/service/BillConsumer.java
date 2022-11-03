package crom.rental.bill.service;

import crom.rental.bill.entity.Bill;
import crom.rental.bill.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static crom.rental.common.utils.JsonUtil.toEntity;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.leftPad;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "save-bill-queue")
class BillConsumer implements MessageListener {
    private final IRentalBillRepository jpaRepo;

    @RabbitHandler(isDefault = true)
    public void saveOldInfoForNextPeriod(Message message) {
        String bodyJson = new String(message.getBody(), StandardCharsets.UTF_8);
        Bill currentBill = toEntity(bodyJson, Bill.class);
        Optional<Bill> nextPeriodBill = prepareNextPeriodData(currentBill);
        nextPeriodBill.ifPresent(jpaRepo::save);
    }

    private Optional<Bill> prepareNextPeriodData(Bill currentBill) {
        Bill nextPeriodBill = new Bill();

        String[] currentPeriodId = currentBill.getId().split("-");
        String roomNo = currentPeriodId[0];
        String month = currentPeriodId[1];
        String year = currentPeriodId[2];
        String newPeriod;
        if (month.equals("12")) {
            newPeriod = "01-" + (parseInt(year) + 1);
        } else {
            newPeriod = leftPad(valueOf(parseInt(month) + 1), 2, "0") + "-" + year;
        }
        nextPeriodBill.setRentalPeriod(newPeriod);
        nextPeriodBill.setId(roomNo + "-" + newPeriod);
        nextPeriodBill.setRoomNumber(currentBill.getRoomNumber());
        nextPeriodBill.setOldElecNumber(currentBill.getCurrentElecNumber());
        nextPeriodBill.setOldWaterNumber(currentBill.getNewWaterNumber());
        nextPeriodBill.setBaseEntity(nextPeriodBill);
        return of(nextPeriodBill);
    }

    @Override
    public void onMessage(Message message) {
    }
}
