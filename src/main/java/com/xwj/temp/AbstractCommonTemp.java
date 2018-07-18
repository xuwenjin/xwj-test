package com.xwj.temp;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.xwj.temp.rule.IBaseRule;

/**
 * @author xuwenjin
 *
 *         模板方法代理demo
 * 
 *         抽象模板
 */
public abstract class AbstractCommonTemp {

	@SuppressWarnings("rawtypes")
	private ThreadLocal local = new ThreadLocal();

	/**
	 * 保证规则处理器是线程安全的
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public RulePack getRulePack() {
		RulePack pack = (RulePack) local.get();
		if (pack == null) {
			pack = new RulePack();
			local.set(pack);
		}
		return pack;
	}

	public void addBeforeRule(IBaseRule rule) {
		if (rule != null) {
			this.getRulePack().addBeforeRule(rule);
		}
	}

	public void addAfterRule(IBaseRule rule) {
		if (rule != null) {
			this.getRulePack().addAfterRule(rule);
		}
	}

	/**
	 * 抽象方法，由子类去实现
	 * 
	 * @param info
	 */
	public abstract void operator(StringBuilder info);

	public final void doAction(StringBuilder info) {
		if (checkBefore()) {
			before(info);
		}

		operator(info);

		after(info);
		System.out.println("最终结果：" + info.toString());
	}

	/**
	 * 钩子函数，提供一个默认或空的实现，具体的子类可以自行决定是否挂钩及如何挂钩
	 * 
	 * @return
	 */
	public boolean checkBefore() {
		return true;
	}

	private void before(StringBuilder info) {
		List<IBaseRule> beforeRules = this.getRulePack().getBefore();
		if (!CollectionUtils.isEmpty(beforeRules)) {
			beforeRules.stream().forEach(d -> {
				d.process(info);
			});
		}
	}

	private void after(StringBuilder info) {
		List<IBaseRule> afterRules = this.getRulePack().getAfter();
		if (!CollectionUtils.isEmpty(afterRules)) {
			afterRules.stream().forEach(d -> {
				d.process(info);
			});
		}
	}

}
