package prs.fltoshi.utils;

import org.jooq.Record;
import prs.fltoshi.models.dto.VehicleDTO;
import prs.fltoshi.models.common.EconomyRewards;
import prs.fltoshi.models.common.EngineStats;
import prs.fltoshi.models.common.WeaponSystem;

import java.util.HashMap;
import java.util.Map;

import static prs.fltoshi.db.generated.tables.Vehicles.VEHICLES;

public class VehicleRecordMapper {

    public static VehicleDTO map(Record record) {
        if (record == null) return null;

        VehicleDTO dto = new VehicleDTO();
        
        dto.setIdentifier(record.get(VEHICLES.IDENTIFIER));
        dto.setCountry(record.get(VEHICLES.COUNTRY));
        dto.setVehicleType(record.get(VEHICLES.VEHICLE_TYPE));
        dto.setEra(record.get(VEHICLES.ERA));

        dto.setArcadeBr(record.get(VEHICLES.ARCADE_BR) != null ? record.get(VEHICLES.ARCADE_BR).doubleValue() : null);
        dto.setRealisticBr(record.get(VEHICLES.REALISTIC_BR) != null ? record.get(VEHICLES.REALISTIC_BR).doubleValue() : null);
        dto.setSimulatorBr(record.get(VEHICLES.SIMULATOR_BR) != null ? record.get(VEHICLES.SIMULATOR_BR).doubleValue() : null);

        dto.setPremium(Boolean.TRUE.equals(record.get(VEHICLES.IS_PREMIUM)));
        dto.setSquadron(Boolean.TRUE.equals(record.get(VEHICLES.IS_SQUADRON)));
        dto.setPack(Boolean.TRUE.equals(record.get(VEHICLES.IS_PACK)));
        dto.setMarketplace(Boolean.TRUE.equals(record.get(VEHICLES.ON_MARKETPLACE)));

        // JSONB Mappings
        WeaponSystem weaponSystem = record.get(VEHICLES.WEAPON_SYSTEM);
        if (weaponSystem != null) {
            dto.setWeapons(weaponSystem.getPrimaryWeapons());
            dto.setPresets(weaponSystem.getWeaponPresets());
            dto.setHasCustomizableWeapons(weaponSystem.isHasCustomizableWeapons());
        }

        EngineStats engine = record.get(VEHICLES.ENGINE_DATA);
        if (engine != null) {
            dto.setEngine(engine);
        }

        EconomyRewards economy = record.get(VEHICLES.ECONOMY_DATA);
        if (economy != null) {
            dto.setSlMulArcade(economy.getSlMulArcade());
            dto.setSlMulRealistic(economy.getSlMulRealistic());
            dto.setSlMulSimulator(economy.getSlMulSimulator());
            dto.setExpMul(economy.getExpMul());
        }

        // Image mapping strategy
        Map<String, String> images = new HashMap<>();
        images.put("image", record.get(VEHICLES.IMAGE_URL));
        images.put("techtree", record.get(VEHICLES.TECH_IMAGE_URL));
        dto.setImages(images);

        return dto;
    }
}
