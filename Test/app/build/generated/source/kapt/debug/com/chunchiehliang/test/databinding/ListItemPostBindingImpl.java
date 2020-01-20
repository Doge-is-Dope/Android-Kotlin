package com.chunchiehliang.test.databinding;
import com.chunchiehliang.test.R;
import com.chunchiehliang.test.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListItemPostBindingImpl extends ListItemPostBinding implements com.chunchiehliang.test.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guideline_left, 4);
        sViewsWithIds.put(R.id.guideline_right, 5);
        sViewsWithIds.put(R.id.guideline_top, 6);
        sViewsWithIds.put(R.id.guideline_bottom, 7);
        sViewsWithIds.put(R.id.img_author, 8);
        sViewsWithIds.put(R.id.img_post, 9);
        sViewsWithIds.put(R.id.img_like, 10);
        sViewsWithIds.put(R.id.img_comment, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListItemPostBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ListItemPostBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.Guideline) bindings[7]
            , (androidx.constraintlayout.widget.Guideline) bindings[4]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textAuthor.setTag(null);
        this.textCommentCount.setTag(null);
        this.textLikeCount.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.chunchiehliang.test.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.clickListener == variableId) {
            setClickListener((com.chunchiehliang.test.ui.home.PostClickListener) variable);
        }
        else if (BR.post == variableId) {
            setPost((com.chunchiehliang.test.domain.Post) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClickListener(@Nullable com.chunchiehliang.test.ui.home.PostClickListener ClickListener) {
        this.mClickListener = ClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.clickListener);
        super.requestRebind();
    }
    public void setPost(@Nullable com.chunchiehliang.test.domain.Post Post) {
        this.mPost = Post;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.post);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        int postId = 0;
        java.lang.String stringValueOfPostId = null;
        com.chunchiehliang.test.ui.home.PostClickListener clickListener = mClickListener;
        com.chunchiehliang.test.domain.Post post = mPost;
        java.lang.String postAuthor = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (post != null) {
                    // read post.id
                    postId = post.getId();
                    // read post.author
                    postAuthor = post.getAuthor();
                }


                // read String.valueOf(post.id)
                stringValueOfPostId = java.lang.String.valueOf(postId);
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textAuthor, postAuthor);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textCommentCount, stringValueOfPostId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textLikeCount, stringValueOfPostId);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // clickListener
        com.chunchiehliang.test.ui.home.PostClickListener clickListener = mClickListener;
        // post
        com.chunchiehliang.test.domain.Post post = mPost;
        // clickListener != null
        boolean clickListenerJavaLangObjectNull = false;



        clickListenerJavaLangObjectNull = (clickListener) != (null);
        if (clickListenerJavaLangObjectNull) {



            clickListener.onClick(post);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): clickListener
        flag 1 (0x2L): post
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}