package com.example.application.views.myview;

import com.example.application.data.SamplePerson;
import com.example.application.services.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("My View")
@Route(value = "my-view", layout = MainLayout.class)
@Uses(Icon.class)
public class MyViewView extends Composite<VerticalLayout> {

    public MyViewView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        TextField textField = new TextField();
        Select select = new Select();
        ComboBox comboBox = new ComboBox();
        NumberField numberField = new NumberField();
        Button buttonSecondary = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Tabs tabs = new Tabs();
        Grid basicGrid = new Grid(SamplePerson.class);
        Button buttonSecondary2 = new Button();
        getContent().setSpacing(false);
        getContent().setPadding(false);
        getContent().setWidthFull();
        getContent().setHeightFull();
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.START);
        layoutRow.addClassName(Gap.LARGE);
        layoutRow.addClassName(Padding.SMALL);
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        h3.setText("Gastos");
        textField.setLabel("Nome");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        select.setLabel("Tipo");
        setSelectSampleData(select);
        comboBox.setLabel("Classificação");
        setComboBoxSampleData(comboBox);
        numberField.setLabel("Valor");
        buttonSecondary.setText("Salvar");
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary);
        layoutColumn2.addClassName(Gap.XLARGE);
        layoutColumn2.addClassName(Padding.XLARGE);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setFlexGrow(1.0, tabs);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, tabs);
        setTabsSampleData(tabs);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.START, basicGrid);
        setGridSampleData(basicGrid);
        buttonSecondary2.setText("Finalizar");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, buttonSecondary2);
        getContent().add(layoutRow);
        layoutRow.add(h3);
        layoutRow.add(textField);
        layoutRow.add(select);
        layoutRow.add(comboBox);
        layoutRow.add(numberField);
        layoutRow.add(buttonSecondary);
        getContent().add(layoutColumn2);
        layoutColumn2.add(tabs);
        layoutColumn2.add(basicGrid);
        getContent().add(buttonSecondary2);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
