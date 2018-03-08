package com.creative.unifymanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ObjectDao implements GenericDao {
  private RedisHelper rh = new RedisHelper();
  //private FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#save(java.lang.String, com.creative.unifymanagement.dao.Object)
 */
@Override
public  void save(String domain, Object data){
    rh.insertByteArr(domain, data.getId(), data.getData().toString().getBytes());
  }
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#get(java.lang.String)
 */
@Override
public Object get(String domain,String key) throws Exception {
    byte[] result = rh.getByteArr(domain, key);
    if(result != null) {
    	Object o = new Object() ;
      if(o.setData(new JSONObject(new String(result))))
        return o;
    }
    return null;
  }

  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#delete(java.lang.String)
 */
@Override
public void delete(String domain,String key){
    rh.deleteKey(domain, key);
  }
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#getAllKeys()
 */
@Override
public Set<String> getAllKeys(String domain){
    return rh.getAll(domain);
  }
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#exist(java.lang.String)
 */
@Override
public boolean exist(String domain,String key) {
    return rh.exist(domain, key);
  }
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#setDomain(java.lang.String)
 */
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#getAll()
 */
@Override
public List<Object> getAll(String domain) throws Exception{
    List<Object> result = new ArrayList<>();
    Set<String> ids = getAllKeys(domain);
    for(String id : ids)
      result.add(get(domain,id));
    return result;
  }
  
  /* (non-Javadoc)
 * @see com.creative.unifymanagement.dao.GenericDao#deleteAll()
 */
@Override
public boolean deleteAll(String domain){
    Set<String> ids = getAllKeys(domain);
    for(String id : ids)
      delete(domain,id);
    return true;
  }
//  @SuppressWarnings("unchecked")
//  public T createObject(Class<T> v) throws Exception{
//    Constructor[] constructors = v.getConstructors();
//    Constructor willBeImplemented = null;
//    for(int i = 0; i <constructors.length; i++){
//      if(constructors[i].getParameterCount() == 0) willBeImplemented = constructors[i];
//    }
//    if(willBeImplemented == null) throw new NoSuchMethodException(v.getName() + " don't have no para constructor");
//    return (T) willBeImplemented.newInstance();
//  }
}
