package com.tananaev.currency;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConvertPresenterTest {

    @Test
    public void onValueChanged_basic() throws Exception {
        ConvertContract.View view = mock(ConvertContract.View.class);
        ConvertContract.Presenter presenter = new ConvertPresenter();
        presenter.setView(view);

        presenter.onValueChanged("2.0", 2.0);

        verify(view).showResult("4.00");
    }

    @Test
    public void onValueChanged_exception() throws Exception {
        ConvertContract.View view = mock(ConvertContract.View.class);
        ConvertContract.Presenter presenter = new ConvertPresenter();
        presenter.setView(view);

        presenter.onValueChanged("", 2.0);

        verify(view).showResult(null);
    }

}
