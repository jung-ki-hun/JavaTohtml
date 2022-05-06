//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;
import org.springframework.lang.Nullable;

final class CollectionToStringConverter implements ConditionalGenericConverter {
    private static final String DELIMITER = ",";
    private final ConversionService conversionService;

    public CollectionToStringConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Collection.class, String.class));
    }

    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getElementTypeDescriptor(), targetType, this.conversionService);
    }

    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        } else {
            Collection<?> sourceCollection = (Collection)source;
            if (sourceCollection.isEmpty()) {
                return "";
            } else {
                StringJoiner sj = new StringJoiner(",");
                Iterator var6 = sourceCollection.iterator();

                while(var6.hasNext()) {
                    Object sourceElement = var6.next();
                    Object targetElement = this.conversionService.convert(sourceElement, sourceType.elementTypeDescriptor(sourceElement), targetType);
                    sj.add(String.valueOf(targetElement));
                }

                return sj.toString();
            }
        }
    }
}