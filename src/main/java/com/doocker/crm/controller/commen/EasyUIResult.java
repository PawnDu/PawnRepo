package com.doocker.crm.controller.commen;
/**
 *  easyUI 返回的数据Bean
 * @author Administrator
 *
 */
public class EasyUIResult {
		//分页接口固定属性
		public long total;
		public  Object rows;
		//判断业务是否成功
		public Boolean flag;
		//业务提示信息
		public String msg;
		
		public EasyUIResult(Long total,Object rows,Boolean flag,String msg){
			this.flag=flag;
			this.msg=msg;
			this.rows=rows;
			this.total=total;
		}
		
		
		public long getTotal() {
			return total;
		}
		public void setTotal(long total) {
			this.total = total;
		}
		public Object getRows() {
			return rows;
		}
		public void setRows(Object rows) {
			this.rows = rows;
		}
		public Boolean getFlag() {
			return flag;
		}
		public void setFlag(Boolean flag) {
			this.flag = flag;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		@Override
		public String toString() {
			return "EasyUIResult [total=" + total + ", rows=" + rows + ", flag=" + flag + ", msg=" + msg + "]";
		}
		
}
