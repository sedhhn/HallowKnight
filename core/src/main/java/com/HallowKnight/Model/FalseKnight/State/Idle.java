package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Idle extends State{
    public Idle(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f, frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        falseKnight.b2Body.setLinearVelocity(0,falseKnight.b2Body.getLinearVelocity().y);
        if (stateTime>falseKnight.getCooldown()) {
            if (falseKnight.getSurroundSensors().radarSensor > 0) {
                chooseNextAction();
            }
        }
    }

    private void chooseNextAction(){
        float distance=Math.abs(falseKnight.getKnight().b2Body.getPosition().x-falseKnight.b2Body.getPosition().x);
        boolean isPhase2=falseKnight.isPhase2();
        Action lastAction=falseKnight.getLastAction();

        //calculating base weights
        float weightMaceSlam = calculateMaceSlamWeight(distance);
        float weightChargeRun = calculateChargeRunWeight(distance);
        float weightOffensiveLeap = calculateOffensiveLeapWeight(distance);
        float weightDefensiveLeap = calculateDefensiveLeapWeight(distance);
        float weightHeavyMaceSlam = isPhase2 ? calculateHeavyMaceSlamWeight(distance) : 0;

        //performing randomness
        weightMaceSlam *= MathUtils.random(0.8f, 1.2f);
        weightChargeRun *= MathUtils.random(0.8f, 1.2f);
        weightOffensiveLeap *= MathUtils.random(0.8f, 1.2f);
        weightDefensiveLeap *= MathUtils.random(0.8f, 1.2f);
        if (isPhase2) weightHeavyMaceSlam *= MathUtils.random(0.8f, 1.2f);

        if (lastAction != null) {
            switch (lastAction) {
                case MACE_SLAM:
                    weightMaceSlam *= 0.1f;
                    break;
                case CHARGE_RUN:
                    weightChargeRun *= 0.1f;
                    break;
                case OFFENSIVE_LEAP:
                    weightOffensiveLeap *= 0.1f;
                    break;
                case DEFENSIVE_LEAP:
                    weightDefensiveLeap *= 0.1f;
                    break;
                case HEAVY_MACE_SLAM:
                    weightHeavyMaceSlam *= 0.1f;
                    break;
            }
        }

            List<Action> actions = new ArrayList<>();
            List<Float> weights = new ArrayList<>();

            actions.add(Action.MACE_SLAM);
            weights.add(weightMaceSlam);

            actions.add(Action.CHARGE_RUN);
            weights.add(weightChargeRun);

            actions.add(Action.OFFENSIVE_LEAP);
            weights.add(weightOffensiveLeap);

            actions.add(Action.DEFENSIVE_LEAP);
            weights.add(weightDefensiveLeap);

            if (isPhase2) {
                actions.add(Action.HEAVY_MACE_SLAM);
                weights.add(weightHeavyMaceSlam);
            }

            Action selectedAction = weightedRandomSelection(actions, weights);

            executeAction(selectedAction);

    }

    private float calculateMaceSlamWeight(float distance){
        if (distance < 3f) return 10f;
        if (distance < 6f) return 7f;
        return 2f;
    }

    private float calculateChargeRunWeight(float distance) {
        if (distance > 8f) return 10f;
        if (distance > 5f) return 7f;
        return 2f;
    }

    private float calculateOffensiveLeapWeight(float distance) {
        if (distance > 4f && distance < 10f) return 8f;
        if (distance >= 10f) return 6f;
        return 3f;
    }

    private float calculateDefensiveLeapWeight(float distance) {
        float baseWeight = 3f;
        float recentDamageWeight = falseKnight.getRecentDamageAmount() * 2f;
        return baseWeight + recentDamageWeight;
    }

    private float calculateHeavyMaceSlamWeight(float distance) {
        if (distance < 5f) return 8f;
        if (distance < 10f) return 6f;
        return 4f;
    }

    private Action weightedRandomSelection(List<Action> actions, List<Float> weights) {
        float totalWeight = 0;
        for (float w : weights) {
            totalWeight += w;
        }

        if (totalWeight <= 0) {
            return Action.MACE_SLAM;
        }

        float random = MathUtils.random(0f, totalWeight);

        float cumulative = 0;
        for (int i = 0; i < actions.size(); i++) {
            cumulative += weights.get(i);
            if (random <= cumulative) {
                return actions.get(i);
            }
        }

        return actions.get(actions.size() - 1);
    }

    private void executeAction(Action action) {
        falseKnight.setLastAction(action);

        switch (action) {
            case MACE_SLAM:
                falseKnight.setState(new MaceSlam(falseKnight));
                break;
            case CHARGE_RUN:
                falseKnight.setState(new ChargeRun(falseKnight));
                break;
            case OFFENSIVE_LEAP:
                falseKnight.setState(new OffensiveLeap(falseKnight));
                break;
            case DEFENSIVE_LEAP:
                falseKnight.setState(new DefensiveLeap(falseKnight));
                break;
            case HEAVY_MACE_SLAM:
                falseKnight.setState(new HeavyMaceSlam(falseKnight));
                break;
        }
    }
}
