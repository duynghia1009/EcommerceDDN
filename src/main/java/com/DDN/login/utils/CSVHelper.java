package com.DDN.login.utils;

import com.DDN.login.model.Order;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static ByteArrayInputStream tutorialsToCSV(List<Order> orders) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for(Order order : orders) {
                List<String> data = Arrays.asList(
                        String.valueOf(order.getId()),
                        String.valueOf(order.getTotalPrice()),
                        order.getUsername(),
                        order.getStatus(),
                        order.getState(),
                        order.getCity(),
                        String.valueOf(order.getCreatedAt()),
                        order.getPhoneNo()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch(IOException e) {
            throw new RuntimeException("Fail to download CSV: " + e.getMessage());
        }

    }
}
