package com.chunchiehliang.test;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007\u00a8\u0006\u0007"}, d2 = {"bindRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "data", "", "Lcom/chunchiehliang/test/domain/Post;", "app_debug"})
public final class BindingAdaptersKt {
    
    /**
     * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
     */
    @androidx.databinding.BindingAdapter(value = {"listData"})
    public static final void bindRecyclerView(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.Nullable()
    java.util.List<com.chunchiehliang.test.domain.Post> data) {
    }
}