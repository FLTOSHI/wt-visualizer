package prs.fltoshi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prs.fltoshi.models.common.*;
import prs.fltoshi.models.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDTO {

    private String identifier;
    private String country;
    @JsonProperty("vehicle_type")
    private String vehicleType;
    @JsonProperty("vehicle_sub_types")
    private List<String> vehicleSubTypes;
    
    private String event;
    @JsonProperty("release_date")
    private String releaseDate;
    private String version;
    private Integer era;

    // БР
    @JsonProperty("arcade_br")
    private Double arcadeBr;
    @JsonProperty("realistic_br")
    private Double realisticBr;
    @JsonProperty("realistic_ground_br")
    private Double realisticGroundBr;
    @JsonProperty("simulator_br")
    private Double simulatorBr;
    @JsonProperty("simulator_ground_br")
    private Double simulatorGroundBr;

    // Статусы
    @JsonProperty("is_premium")
    private boolean premium;
    @JsonProperty("squadron_vehicle")
    private boolean squadron;
    @JsonProperty("is_pack")
    private boolean pack;
    @JsonProperty("on_marketplace")
    private boolean marketplace;

    // Экономика
    private Integer value;
    @JsonProperty("req_exp")
    private Integer reqExp;
    @JsonProperty("ge_cost")
    private Integer geCost;
    
    @JsonProperty("sl_mul_arcade")
    private Double slMulArcade;
    @JsonProperty("sl_mul_realistic")
    private Double slMulRealistic;
    @JsonProperty("sl_mul_simulator")
    private Double slMulSimulator;
    @JsonProperty("exp_mul")
    private Double expMul;

    // Ремонт (Экономика)
    @JsonProperty("repair_time_arcade") private Double rTimeArcade;
    @JsonProperty("repair_time_realistic") private Double rTimeRealistic;
    @JsonProperty("repair_time_simulator") private Double rTimeSimulator;
    @JsonProperty("repair_time_no_crew_arcade") private Double rTimeNoCrewArcade;
    @JsonProperty("repair_time_no_crew_realistic") private Double rTimeNoCrewRealistic;
    @JsonProperty("repair_time_no_crew_simulator") private Double rTimeNoCrewSimulator;
    
    @JsonProperty("repair_cost_arcade") private Integer rCostArcade;
    @JsonProperty("repair_cost_realistic") private Integer rCostRealistic;
    @JsonProperty("repair_cost_simulator") private Integer rCostSimulator;
    @JsonProperty("repair_cost_per_min_arcade") private Integer rCostPerMinArcade;
    @JsonProperty("repair_cost_per_min_realistic") private Integer rCostPerMinRealistic;
    @JsonProperty("repair_cost_per_min_simulator") private Integer rCostPerMinSimulator;
    @JsonProperty("repair_cost_full_upgraded_arcade") private Integer rCostFullArcade;
    @JsonProperty("repair_cost_full_upgraded_realistic") private Integer rCostFullRealistic;
    @JsonProperty("repair_cost_full_upgraded_simulator") private Integer rCostFullSimulator;

    // Обучение
    @JsonProperty("train1_cost") private Integer train1Cost;
    @JsonProperty("train2_cost") private Integer train2Cost;
    @JsonProperty("train3_cost_gold") private Integer train3CostGold;
    @JsonProperty("train3_cost_exp") private Integer train3CostExp;

    // Экипаж
    @JsonProperty("crew_total_count") private Integer crewCount;
    private Integer visibility;

    // Броня и масса
    @JsonProperty("hull_armor") private int[] hullArmor;
    @JsonProperty("turret_armor") private int[] turretArmor;
    private double mass;

    // Системы
    private EngineStats engine;
    private List<Modification> modifications;
    private List<Weapon> weapons;
    private List<WeaponPreset> presets;
    @JsonProperty("has_customizable_weapons") private boolean hasCustomizableWeapons;
    @JsonProperty("required_vehicle") private String requiredVehicle;
    private List<String> versions;

    // Изображения
    private Map<String, String> images;

    public Vehicle toDomainModel() {
        Vehicle v = createVehicleInstance();

        v.setIdentifier(this.identifier);
        v.setCountry(this.country);
        v.setVehicleType(this.vehicleType);
        v.setVehicleSubType(this.vehicleSubTypes != null ? this.vehicleSubTypes : new ArrayList<>());
        v.setEra(this.era != null ? this.era : 0);
        v.setArcadeBr(this.arcadeBr != null ? this.arcadeBr : 0.0);
        v.setRealisticBr(this.realisticBr != null ? this.realisticBr : 0.0);
        v.setRealisticGroundBr(this.realisticGroundBr != null ? this.realisticGroundBr : 0.0);
        v.setSimulatorBr(this.simulatorBr != null ? this.simulatorBr : 0.0);
        v.setSimulatorGroundBr(this.simulatorGroundBr != null ? this.simulatorGroundBr : 0.0);
        
        v.setExpValue(this.reqExp != null ? this.reqExp : 0);
        v.setSlValue(this.value != null ? this.value : 0);
        v.setGeValue(this.geCost != null ? this.geCost : 0);

        v.setPremium(this.premium);
        v.setSquadron(this.squadron);
        v.setPack(this.pack);
        v.setOnMarketplace(this.marketplace);
        
        v.setReleaseDate(this.releaseDate);
        v.setVersion(this.version);
        v.setVisibility(this.visibility != null ? this.visibility : 0);
        v.setRequiredVehicle(this.requiredVehicle);
        v.setMass(this.mass);
        v.setHullArmor(this.hullArmor);
        v.setTurretArmor(this.turretArmor);
        v.setVersions(this.versions);
        v.setHasCustomizableWeapons(this.hasCustomizableWeapons);

        // 1. Экономика
        v.setRewards(EconomyRewards.builder()
            .slMulArcade(this.slMulArcade != null ? this.slMulArcade : 0.0)
            .slMulRealistic(this.slMulRealistic != null ? this.slMulRealistic : 0.0)
            .slMulSimulator(this.slMulSimulator != null ? this.slMulSimulator : 0.0)
            .expMul(this.expMul != null ? this.expMul : 0.0)
            .build());

        // 2. Ремонт
        v.setRepair(RepairStats.builder()
            .costArcade(this.rCostArcade != null ? this.rCostArcade : 0)
            .costRealistic(this.rCostRealistic != null ? this.rCostRealistic : 0)
            .costSimulator(this.rCostSimulator != null ? this.rCostSimulator : 0)
            .costPerMinuteArcade(this.rCostPerMinArcade != null ? this.rCostPerMinArcade : 0)
            .costPerMinuteRealistic(this.rCostPerMinRealistic != null ? this.rCostPerMinRealistic : 0)
            .costPerMinuteSimulator(this.rCostPerMinSimulator != null ? this.rCostPerMinSimulator : 0)
            .fullUpgradeCostArcade(this.rCostFullArcade != null ? this.rCostFullArcade : 0)
            .fullUpgradeCostRealistic(this.rCostFullRealistic != null ? this.rCostFullRealistic : 0)
            .fullUpgradeCostSimulator(this.rCostFullSimulator != null ? this.rCostFullSimulator : 0)
            .timeArcade(this.rTimeArcade != null ? this.rTimeArcade : 0.0)
            .timeRealistic(this.rTimeRealistic != null ? this.rTimeRealistic : 0.0)
            .timeSimulator(this.rTimeSimulator != null ? this.rTimeSimulator : 0.0)
            .timeNoCrewArcade(this.rTimeNoCrewArcade != null ? this.rTimeNoCrewArcade : 0.0)
            .timeNoCrewRealistic(this.rTimeNoCrewRealistic != null ? this.rTimeNoCrewRealistic : 0.0)
            .timeNoCrewSimulator(this.rTimeNoCrewSimulator != null ? this.rTimeNoCrewSimulator : 0.0)
            .build());

        // 3. Экипаж
        v.setCrew(CrewService.builder()
            .crewTotalCount(this.crewCount != null ? this.crewCount : 0)
            .train1Cost(this.train1Cost != null ? this.train1Cost : 0)
            .train2Cost(this.train2Cost != null ? this.train2Cost : 0)
            .train3CostGold(this.train3CostGold != null ? this.train3CostGold : 0)
            .train3CostExp(this.train3CostExp != null ? this.train3CostExp : 0)
            .build());

        // 4. Изображения
        if (images != null) {
            v.setVehicleSingleImageUrl(images.get("image"));
            v.setVehicleTechImageUrl(images.get("techtree"));
        }

        // 5. Системы
        v.setEngine(this.engine);
        v.setModifications(this.modifications != null ? this.modifications : new ArrayList<>());
        
        v.setWeaponSystem(WeaponSystem.builder()
            .primaryWeapons(this.weapons != null ? this.weapons : new ArrayList<>())
            .weaponPresets(this.presets != null ? this.presets : new ArrayList<>())
            .hasCustomizableWeapons(this.hasCustomizableWeapons)
            .build());

        return v;
    }

    private Vehicle createVehicleInstance() {
        String type = (vehicleType != null) ? vehicleType.toLowerCase() : "";
        if (List.of("assault", "fighter", "bomber", "strike_aircraft").contains(type))
            return new AircraftVehicle();
        if (type.contains("tank") || type.contains("spaa") || type.contains("tank_destroyer"))
            return new GroundVehicle();
        if (type.contains("helicopter"))
            return new HelicopterVehicle();
        if (type.contains("ship") || type.contains("boat") || type.contains("cruiser") || type.contains("frigate"))
            return new NavalVehicle();
        return new Vehicle();
    }
}