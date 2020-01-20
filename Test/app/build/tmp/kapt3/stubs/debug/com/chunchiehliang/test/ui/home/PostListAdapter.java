package com.chunchiehliang.test.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/chunchiehliang/test/ui/home/PostListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/chunchiehliang/test/domain/Post;", "Lcom/chunchiehliang/test/ui/home/PostListAdapter$PostListViewHolder;", "clickListener", "Lcom/chunchiehliang/test/ui/home/PostClickListener;", "(Lcom/chunchiehliang/test/ui/home/PostClickListener;)V", "getClickListener", "()Lcom/chunchiehliang/test/ui/home/PostClickListener;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "PostListViewHolder", "app_debug"})
public final class PostListAdapter extends androidx.recyclerview.widget.ListAdapter<com.chunchiehliang.test.domain.Post, com.chunchiehliang.test.ui.home.PostListAdapter.PostListViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final com.chunchiehliang.test.ui.home.PostClickListener clickListener = null;
    public static final com.chunchiehliang.test.ui.home.PostListAdapter.DiffCallback DiffCallback = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.chunchiehliang.test.ui.home.PostListAdapter.PostListViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.chunchiehliang.test.ui.home.PostListAdapter.PostListViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.chunchiehliang.test.ui.home.PostClickListener getClickListener() {
        return null;
    }
    
    public PostListAdapter(@org.jetbrains.annotations.NotNull()
    com.chunchiehliang.test.ui.home.PostClickListener clickListener) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/chunchiehliang/test/ui/home/PostListAdapter$PostListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/chunchiehliang/test/databinding/ListItemPostBinding;", "(Lcom/chunchiehliang/test/databinding/ListItemPostBinding;)V", "bind", "", "listener", "Lcom/chunchiehliang/test/ui/home/PostClickListener;", "post", "Lcom/chunchiehliang/test/domain/Post;", "Companion", "app_debug"})
    public static final class PostListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private com.chunchiehliang.test.databinding.ListItemPostBinding binding;
        public static final com.chunchiehliang.test.ui.home.PostListAdapter.PostListViewHolder.Companion Companion = null;
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.ui.home.PostClickListener listener, @org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.domain.Post post) {
        }
        
        public PostListViewHolder(@org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.databinding.ListItemPostBinding binding) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/chunchiehliang/test/ui/home/PostListAdapter$PostListViewHolder$Companion;", "", "()V", "from", "Lcom/chunchiehliang/test/ui/home/PostListAdapter$PostListViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.chunchiehliang.test.ui.home.PostListAdapter.PostListViewHolder from(@org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/chunchiehliang/test/ui/home/PostListAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/chunchiehliang/test/domain/Post;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.chunchiehliang.test.domain.Post> {
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.domain.Post oldItem, @org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.domain.Post newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.domain.Post oldItem, @org.jetbrains.annotations.NotNull()
        com.chunchiehliang.test.domain.Post newItem) {
            return false;
        }
        
        private DiffCallback() {
            super();
        }
    }
}