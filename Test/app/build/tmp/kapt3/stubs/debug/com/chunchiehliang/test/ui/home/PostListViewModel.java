package com.chunchiehliang.test.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/chunchiehliang/test/ui/home/PostListViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_postList", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/chunchiehliang/test/domain/Post;", "currentJob", "Lkotlinx/coroutines/Job;", "postList", "Landroidx/lifecycle/LiveData;", "getPostList", "()Landroidx/lifecycle/LiveData;", "onQueryChanged", "", "app_debug"})
public final class PostListViewModel extends androidx.lifecycle.ViewModel {
    private kotlinx.coroutines.Job currentJob;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.chunchiehliang.test.domain.Post>> _postList = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.chunchiehliang.test.domain.Post>> getPostList() {
        return null;
    }
    
    private final void onQueryChanged() {
    }
    
    public PostListViewModel() {
        super();
    }
}