package com.sap.capire.bookshop;

import cds.gen.catalogservice.Books;
import cds.gen.catalogservice.Books_;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.annotations.After;
import java.util.List;
import org.springframework.stereotype.Component;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.ServiceName;
import cds.gen.catalogservice.CatalogService_;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

  private static final String DISCOUNT_MESSAGE = " -- 11% discount!";

  @After(event = CqnService.EVENT_READ, entity = Books_.CDS_NAME)
  public void addDiscountIfApplicable(List<Books> books) {
    for (Books book : books) {
      if (book.getStock() != null && book.getStock() > 20) {
        book.setTitle(book.getTitle() + DISCOUNT_MESSAGE);
      }
    }
  }
}
