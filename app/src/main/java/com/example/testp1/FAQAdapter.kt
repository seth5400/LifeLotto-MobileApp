package com.example.testp1

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class FAQAdapter(

    private val context: Context,
    private val listDataHeader: List<String>,
    private val listDataChild: HashMap<String, List<FAQItem>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return listDataHeader.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listDataChild[listDataHeader[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return listDataHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listDataChild[listDataHeader[groupPosition]]?.get(childPosition)
            ?: throw IllegalArgumentException("Child not found")
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val headerText = listDataHeader[groupPosition]
        val view = convertView ?: View.inflate(context, R.layout.list_item_faq_group, null)

        val questionTextView: TextView = view.findViewById(R.id.questionTextView)
        questionTextView.text = headerText

        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childText = (getChild(groupPosition, childPosition) as FAQItem).answer
        val view = convertView ?: View.inflate(context, R.layout.list_item_faq_child, null)

        val answerTextView: TextView = view.findViewById(R.id.answerTextView)
        answerTextView.text = childText

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}