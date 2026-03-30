package prs.fltoshi.repositories.impl;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import prs.fltoshi.models.types.Vehicle;
import prs.fltoshi.repositories.VehicleRepository;
import prs.fltoshi.services.VehicleSearchService.SearchCriteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static prs.fltoshi.db.generated.tables.Vehicles.VEHICLES;

public class JooqVehicleRepository implements VehicleRepository {
    private final DSLContext dsl;

    public JooqVehicleRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void save(Vehicle vehicle) {
        // Логика сохранения (insert/update)
        // В реальном проекте здесь был бы upsert
    }

    @Override
    public Optional<Vehicle> findByIdentifier(String identifier) {
        return dsl.selectFrom(VEHICLES)
                .where(VEHICLES.IDENTIFIER.eq(identifier))
                .fetchOptionalInto(Vehicle.class);
    }

    @Override
    public List<Vehicle> findByCountry(String country) {
        return dsl.selectFrom(VEHICLES)
                .where(VEHICLES.COUNTRY.eq(country))
                .fetchInto(Vehicle.class);
    }

    @Override
    public List<Vehicle> findByType(String type) {
        return dsl.selectFrom(VEHICLES)
                .where(VEHICLES.VEHICLE_TYPE.eq(type))
                .fetchInto(Vehicle.class);
    }

    @Override
    public List<Vehicle> findByRank(int rank) {
        return dsl.selectFrom(VEHICLES)
                .where(VEHICLES.ERA.eq(rank))
                .fetchInto(Vehicle.class);
    }

    @Override
    public List<Vehicle> findAll() {
        return dsl.selectFrom(VEHICLES)
                .fetchInto(Vehicle.class);
    }

    @Override
    public List<prs.fltoshi.models.dto.VehicleDTO> findByCriteria(SearchCriteria criteria) {
        Condition condition = DSL.noCondition();

        if (criteria.country() != null) {
            condition = condition.and(VEHICLES.COUNTRY.eq(criteria.country()));
        }
        if (criteria.type() != null) {
            condition = condition.and(VEHICLES.VEHICLE_TYPE.eq(criteria.type()));
        }
        if (criteria.rank() != null) {
            condition = condition.and(VEHICLES.ERA.eq(criteria.rank()));
        }
        
        if (criteria.minBr() != null) {
            condition = condition.and(VEHICLES.REALISTIC_BR.ge(BigDecimal.valueOf(criteria.minBr())));
        }
        if (criteria.maxBr() != null) {
            condition = condition.and(VEHICLES.REALISTIC_BR.le(BigDecimal.valueOf(criteria.maxBr())));
        }

        if (criteria.query() != null && !criteria.query().isBlank()) {
            String searchPattern = "%" + criteria.query().trim() + "%";
            condition = condition.and(
                VEHICLES.IDENTIFIER.likeIgnoreCase(searchPattern)
                .or(DSL.replace(VEHICLES.IDENTIFIER, "-", "").likeIgnoreCase(searchPattern))
            );
        }

        return dsl.selectFrom(VEHICLES)
                .where(condition)
                .orderBy(VEHICLES.REALISTIC_BR.asc())
                .limit(100)
                .fetch()
                .map(prs.fltoshi.utils.VehicleRecordMapper::map);
    }
}