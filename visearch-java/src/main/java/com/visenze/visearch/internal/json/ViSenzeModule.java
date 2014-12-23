package com.visenze.visearch.internal.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.visenze.visearch.*;

public class ViSenzeModule extends SimpleModule {

    public ViSenzeModule() {
        super("ViSenzeModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Image.class, ImageMixin.class);
        context.setMixInAnnotations(ImageResult.class, ImageResultMixin.class);
        context.setMixInAnnotations(Facet.class, FacetMixin.class);
        context.setMixInAnnotations(FacetItem.class, FacetItemMixin.class);

        context.setMixInAnnotations(InsertTransaction.class, InsertTransactionMixin.class);
    }
}