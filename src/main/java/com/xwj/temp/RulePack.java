package com.xwj.temp;

import java.util.ArrayList;
import java.util.List;

import com.xwj.temp.rule.IBaseRule;

/**
 * 
 * @author xuwenjin
 *
 *         封装前后规则，为了多线程安全
 */
public class RulePack {
	
	private List<IBaseRule> before = new ArrayList<>(); // 保存前规则
	private List<IBaseRule> after = new ArrayList<>(); // 保存后规则
	
	public void addBeforeRule(IBaseRule rule) {
		before.add(rule);
	}
	
	public void addAfterRule(IBaseRule rule) {
		after.add(rule);
	}
	
	public List<IBaseRule> getBefore() {
		return before;
	}
	
	public void setBefore(List<IBaseRule> before) {
		this.before = before;
	}
	
	public List<IBaseRule> getAfter() {
		return after;
	}
	
	public void setAfter(List<IBaseRule> after) {
		this.after = after;
	}
	
}
