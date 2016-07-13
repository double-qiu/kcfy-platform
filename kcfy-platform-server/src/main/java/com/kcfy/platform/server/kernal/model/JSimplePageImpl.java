package com.kcfy.platform.server.kernal.model;

import java.util.ArrayList;
import java.util.List;

import com.kcfy.platform.server.cache.proext.PropertyExtendable;

public class JSimplePageImpl<T> implements JPage<T>,PropertyExtendable{

	private int pageSize = 10; // 每页的记录数

    private long start; // 当前页第一条数据在List中的位置,从0开始

    private List<T> data = new ArrayList<T>(); // 当前页中存放的记录,类型一般为List

    private long resultCount; // 总记录数
	
    private long pageIndex;
    
	@Override
	public JPageable getPageable() {
		return null;
	}

	@Override
	public List<T> getContent() {
		return data;
	}

	@Override
	public int getTotalPageNumber() {
		return Integer.valueOf(String.valueOf(getPageCount()));
	}

	@Override
	public long getTotalRecordNumber() {
		return resultCount;
	}

	 public long getPageCount() {
	        if (resultCount % pageSize == 0) {
	            return resultCount / pageSize;
	        } else {
	            return resultCount / pageSize + 1;
	        }
	 }
	
	 public long getResultCount() {
	        return this.resultCount;
	    }
	 public long getStart() {
	        return start;
	    }
	 public int getPageSize() {
	        return pageSize;
	    }
	 
	 public List<T> getData() {
	        return data;
	    }

	    /**
	     * 取该页当前页码，0为第一页
	     * @return 当前页的页码
	     */
	    public long getPageIndex() {
	        return pageIndex;
	    }
	    
	    public void setPageIndex(long pageIndex) {
			this.pageIndex = pageIndex;
		}

	    /**
	     * 该页是否有下一页.
	     * @return 如果当前页是最后一页，返回false，否则返回true
	     */
	    public boolean hasNextPage() {
	        return this.getPageIndex() < this.getPageCount() - 1;
	    }

	    /**
	     * 该页是否有上一页.
	     * @return 如果当前页码为0，返回true，否则返回false
	     */
	    public boolean hasPreviousPage() {
	        return this.getPageIndex() > 0;
	    }

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public void setStart(long start) {
			this.start = start;
		}

		public void setData(List<T> data) {
			this.data = data;
		}

		public void setResultCount(long resultCount) {
			this.resultCount = resultCount;
		}

}
