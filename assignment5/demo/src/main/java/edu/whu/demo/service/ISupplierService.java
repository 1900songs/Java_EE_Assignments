package edu.whu.demo.service;

import edu.whu.demo.domain.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
public interface ISupplierService extends IService<Supplier> {
    public Supplier addSupplier(Supplier mySupplier);
    public void removeSupplier(int id);
    public void updateSupplier(Supplier mySupplier);
    public Supplier getSupplier(int id);
}
